package id.saipulmuiz.forwaapp.ui.members.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.data.model.Status
import id.saipulmuiz.forwaapp.databinding.FragmentMemberDetailBinding
import id.saipulmuiz.forwaapp.ui.base.BaseFragment
import id.saipulmuiz.forwaapp.util.hide
import id.saipulmuiz.forwaapp.util.show
import id.saipulmuiz.forwaapp.util.showDialogWarning

class MemberDetailFragment : BaseFragment<FragmentMemberDetailBinding, MemberDetailViewModel>() {

    override var getLayoutId: Int = R.layout.fragment_member_detail
    override var getViewModel: Class<MemberDetailViewModel> = MemberDetailViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Detail Member")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    //    Set condition PlaceholderView
        setContentPlaceholder(1)

    //    Configure mViewBnding
        mViewBinding.apply {
            arguments?.let {
                val user = MemberDetailFragmentArgs.fromBundle(it).user
                val username = MemberDetailFragmentArgs.fromBundle(it).username
                if (user != null){
                    setContentPlaceholder(1)
                    this.user = user
                } else{
                    observeDetail(username)
                }
            }

            profileBtnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun observeDetail(username: String) {
        mViewBinding.apply {
            profileProgressBar.show()

            mViewModel.getDetail(username)
                .observe(viewLifecycleOwner, Observer {
                    it?.let {status ->
                        when(status.status) {
                            Status.StatusType.SUCCESS -> {
                                it.data?.let { data ->
                                    profileProgressBar.hide()
                                    setContentPlaceholder(1)
                                    user = data
                                }
                            }
                            Status.StatusType.ERROR -> {
                            //    Hide progressBar and show warning dialog
                                profileProgressBar.hide()
                                showDialogWarning(mDialog, status.message ?: "Error", null)
                                setContentPlaceholder(2)
                            }
                        }
                    }
                })
        }
    }
}