package id.saipulmuiz.forwaapp.ui.members.member

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.data.adapter.UserSearchAdapter
import id.saipulmuiz.forwaapp.data.model.Status
import id.saipulmuiz.forwaapp.data.model.UserSearch
import id.saipulmuiz.forwaapp.databinding.FragmentMemberBinding
import id.saipulmuiz.forwaapp.ui.base.BaseFragment
import id.saipulmuiz.forwaapp.util.*
import kotlinx.android.synthetic.main.fragment_member.*

// Home fragment implements dagger fragment
class MemberFragment : BaseFragment<FragmentMemberBinding, MemberViewModel>(),
    UserSearchAdapter.Listener, SwipeRefreshLayout.OnRefreshListener {

    private var rvUserSearchAdapter = UserSearchAdapter(this)
    private lateinit var layoutManagers: LinearLayoutManager
    private var page = 1
    private var totalPage: Int = 1
    private var isLoading = false
    private var searchText = ""

    override var getLayoutId: Int = R.layout.fragment_member
    override var getViewModel: Class<MemberViewModel> = MemberViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Member")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        layoutManagers = LinearLayoutManager(context)

        // Set condition PlaceholderView
        setContentPlaceholder(1)

        swipeRefresh.setOnRefreshListener(this)
        userDefault()

        mViewBinding.apply {
            nestedScroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
                Log.e("DY", scrollY.toString())
                if (v.getChildAt(v.childCount - 1) != null) {
                    val maxScroll =
                        (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)
                    Log.e("PATOKAN", maxScroll.toString())
                    if (scrollY >= maxScroll && scrollY > oldScrollY) {
                        val visibleItemCount = layoutManagers.childCount
                        val total = layoutManagers.itemCount
                        val pastVisibleItem = layoutManagers.findFirstVisibleItemPosition()
                        if (!isLoading && page < totalPage) {
                            if (visibleItemCount + pastVisibleItem >= total) {
                                page++
                                if (searchText != "") {
                                    getUsers(false, searchText)
                                } else {
                                    userDefault()
                                }
                                Log.e("Page", page.toString())
                                Log.e("VISIBLE ITEM COUNT", visibleItemCount.toString())
                                Log.e("PAST ITEM", pastVisibleItem.toString())
                                Log.e("IS LOADING", isLoading.toString())
                                Log.e("TOTAL", total.toString())
                            }
                        }
                    }
                }
            })
        }
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
                setHasFixedSize(true)
                layoutManager = layoutManagers
                adapter = rvUserSearchAdapter
            }

            // Configure handle search EditText
            homeEtSearch.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    var handled = false

                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        searchText = this.text.toString()
                        searchUser()
                        handled = true
                    }
                    handled
                }
            }

            // Set button search clickListener
            homeBtnSearch.setOnClickListener {
                searchText = homeEtSearch.text.toString()
                searchUser()
            }
        }
    }

    // Function : for default user
    private fun userDefault() {
        mViewBinding.apply {
            memberRvUser.requestFocus()
            getUsers(false, "")
        }
    }

    // Function : for search user
    private fun searchUser() {
        mViewBinding.apply {
            if (!homeEtSearch.text.isBlank()) {
                nestedScroll.smoothScrollTo(0, 0)
                memberRvUser.requestFocus()
                shimmerMember.startShimmer()
                shimmerMember.show()
                memberProgressBar.show()
                Log.e("SEARCH TEXT", searchText)
                context?.hideKeyboard(requireView())

                page = 1
                rvUserSearchAdapter.clear()
                getUsers(false, searchText)
            }
        }
    }

    private fun getUsers(isOnRefresh: Boolean, keyword: String) {
        isLoading = true
        if (!isOnRefresh) mViewBinding.memberProgressBar.show()
        mViewModel.getUserSearch(keyword, page.toString())
            .observe(viewLifecycleOwner, {
                it?.let { status ->
                    when (status.status) {
                        Status.StatusType.SUCCESS -> {
                            Log.e("PAGE", "PAGE: $page")

                            // Hide ProgressBar and set list data in RecyclerViewAdapter
                            it.data?.let { data ->
                                totalPage = data.total_pages
                                Log.e("PAGE", "totalPage: $totalPage")

                                if (data.total_count > 0) {
                                    // When data is not empty
                                    setContentPlaceholder(1)
                                    rvUserSearchAdapter.addList(data.items)
                                } else {
                                    // When data is empty
                                    setContentPlaceholder(4)
                                }
                                hideLoading()
                            }
                        }
                        Status.StatusType.ERROR -> {

                            // Hide ProgressBar and show warning dialog
                            showDialogWarning(mDialog, status.message ?: "Error", null)
                            hideLoading()
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

    override fun onRefresh() {
        mViewBinding.homeEtSearch.text = null
        searchText = ""
        page = 1
        rvUserSearchAdapter.clear()
        getUsers(true, "")
    }

    private fun hideLoading() {
        mViewBinding.shimmerMember.stopShimmer()
        mViewBinding.shimmerMember.hide()
        mViewBinding.memberProgressBar.hide()
        isLoading = false
        swipeRefresh.isRefreshing = false
    }
}
