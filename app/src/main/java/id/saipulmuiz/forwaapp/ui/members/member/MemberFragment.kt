package id.saipulmuiz.forwaapp.ui.members.member

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isEmpty
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.data.adapter.UserSearchAdapter
import id.saipulmuiz.forwaapp.data.model.Status
import id.saipulmuiz.forwaapp.data.model.UserSearch
import id.saipulmuiz.forwaapp.databinding.FragmentMemberBinding
import id.saipulmuiz.forwaapp.ui.base.BaseFragment
import id.saipulmuiz.forwaapp.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_member.*

// Home fragment implements dagger fragment
class MemberFragment : BaseFragment<FragmentMemberBinding, MemberViewModel>(),
    UserSearchAdapter.Listener {

    private var rvUserSearchAdapter = UserSearchAdapter(this)

    override var getLayoutId: Int = R.layout.fragment_member
    override var getViewModel: Class<MemberViewModel> = MemberViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Member")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true

        // Set condition PlaceholderView
        setContentPlaceholder(if (rvUserSearchAdapter.itemCount <= 0) 3 else 1)

        userDefault()
        // Configure ViewBinding
        mViewBinding.apply {

            // Inflate options menu & set optionsClickListener
            homeToolbar.apply {
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_home_setting -> {
                            val action =
                                MemberFragmentDirections.actionMemberFragmentToSettingFragment()
                            view.changeNavigation(action)
                        }
                    }
                    false
                }
            }

            // Set adapter user RecyclerView
            memberRvUser.apply {
                adapter = rvUserSearchAdapter
            }

            // Configure handle search EditText
            homeEtSearch.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    var handled = false

                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        searchUser()
                        handled = true
                    }
                    handled
                }
            }

            // Set button search clickListener
            homeBtnSearch.setOnClickListener {
                searchUser()
            }
        }
    }

    // Function : for search user
    private fun searchUser() {
        mViewBinding.apply {
            if (!homeEtSearch.text.isBlank()) {
                memberRvUser.requestFocus()

                memberProgressBar.show()
                context?.hideKeyboard(requireView())

                observeUserSearch(homeEtSearch.text.toString())
            }
        }
    }

    private fun userDefault() {
        mViewBinding.apply {
            memberRvUser.requestFocus()
            memberProgressBar.show()
            observeUserSearch("")
        }
    }

    // Function : for observe data user search from api
    private fun observeUserSearch(keyword: String) {
        mViewModel.getUserSearch(keyword)
            .observe(viewLifecycleOwner, Observer {
                it?.let { status ->
                    when (status.status) {
                        Status.StatusType.SUCCESS -> {

                            // Hide ProgressBar and set list data in RecyclerViewAdapter
                            it.data?.let { data ->
                                mViewBinding.memberProgressBar.hide()

                                if (data.total_count > 0) {
                                    // When data is not empty
                                    setContentPlaceholder(1)
                                    rvUserSearchAdapter.setList(data.items)
                                } else {
                                    // When data is empty
                                    setContentPlaceholder(4)
                                }
                            }
                        }
                        Status.StatusType.ERROR -> {

                            // Hide ProgressBar and show warning dialog
                            mViewBinding.memberProgressBar.hide()

                            showDialogWarning(mDialog, status.message ?: "Error", null)
                            setContentPlaceholder(2)
                        }
                    }
                }
            })
    }

    override fun onUserClickListener(view: View, data: UserSearch) {
        val action =
            MemberFragmentDirections.actionMemberFragmentToUserDetailFragment(data.username, null)
        view.changeNavigation(action)
    }
}
