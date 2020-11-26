package id.saipulmuiz.forwaapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import id.saipulmuiz.forwaapp.di.viewmodel.ViewModelProviderFactory
import id.saipulmuiz.forwaapp.ui.MainActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

// Base Fragment for implements DaggerFragment; Keyword : Base
abstract class BaseFragment<T : ViewDataBinding, VM : ViewModel> : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var mViewBinding: T
    lateinit var mViewModel: VM
    lateinit var mDialog: SweetAlertDialog

    abstract var getLayoutId: Int
    abstract var getViewModel: Class<VM>
    abstract var title: MutableLiveData<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Set View Binding & View Model
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        mViewModel = ViewModelProvider(this, factory)[getViewModel]

        // Set mDialog to get dialog from MainActivity
        mDialog = (activity as MainActivity).mDialog

        // Observe tittle and set into toolbar when change
        title.observe(viewLifecycleOwner, Observer {
            setToolbar(it)
        })

        return mViewBinding.root
    }

    // Function : for change tittle toolbar
    private fun setToolbar(tittle: String) = (activity as MainActivity).changeToolbarTitle(tittle)

    // Function : for set content placeholder view
    fun setContentPlaceholder(condition: Int) {
        (activity as MainActivity).setContentPlaceholder(condition, mViewBinding)
    }

}