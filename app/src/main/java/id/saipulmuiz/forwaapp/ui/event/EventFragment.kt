package id.saipulmuiz.forwaapp.ui.event

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.shashank.sony.fancytoastlib.FancyToast
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.data.adapter.EventListAdapter
import id.saipulmuiz.forwaapp.data.model.EventList
import id.saipulmuiz.forwaapp.data.model.Status
import id.saipulmuiz.forwaapp.databinding.FragmentEventBinding
import id.saipulmuiz.forwaapp.ui.base.BaseFragment
import id.saipulmuiz.forwaapp.util.changeNavigation
import id.saipulmuiz.forwaapp.util.hide
import id.saipulmuiz.forwaapp.util.show
import id.saipulmuiz.forwaapp.util.showDialogWarning

class EventFragment : BaseFragment<FragmentEventBinding, EventViewModel>(),
    EventListAdapter.Listener {

    private var rvEventListAdapter = EventListAdapter(this)

    override var getLayoutId: Int = R.layout.fragment_event
    override var getViewModel: Class<EventViewModel> = EventViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Event")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        eventList()

        //    Set condition PlaceholderView
        setContentPlaceholder(1)

        mViewBinding.apply {
            eventToolbar.apply {
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_home_setting -> {

                        }
                    }
                    false
                }
            }

            eventRvItem.apply {
                adapter = rvEventListAdapter
            }
        }
    }

    private fun eventList() {
        mViewBinding.apply {
            baseLoading.show()
            eventRvItem.hide()
            observerEvent()
        }
    }

    private fun observerEvent() {
        mViewModel.getEventList()
            .observe(viewLifecycleOwner, Observer {
                it?.let { status ->
                    when (status.status) {
                        Status.StatusType.SUCCESS -> {
                            it.data?.let { data ->
                                mViewBinding.baseLoading.hide()

                                if (data.total_count > 0) {
                                    mViewBinding.eventRvItem.show()
                                    setContentPlaceholder(1)
                                    rvEventListAdapter.setList(data.items)
                                } else {
                                    setContentPlaceholder(4)
                                }
                            }
                        }
                        Status.StatusType.ERROR -> {
                            mViewBinding.baseLoading.hide()
                            showDialogWarning(mDialog, status.message ?: "Error", null)
                            setContentPlaceholder(2)
                        }
                    }
                }
            })
    }

    override fun onEventClickListener(view: View, data: EventList) {
        FancyToast.makeText(
            activity,
            "Data " + data.event_id + " Di klik!",
            FancyToast.LENGTH_LONG,
            FancyToast.SUCCESS,
            true
        ).show()
        val action =
            EventFragmentDirections.actionEventFragmentToEventDetailFragment(data.event_id, null)
        view.changeNavigation(action)
    }
}