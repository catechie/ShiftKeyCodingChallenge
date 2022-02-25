package com.shiftkey.codingchallenge

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.shiftkey.codingchallenge.databinding.FragmentShiftListBinding
import com.shiftkey.data.Data

/**
 * A simple [Fragment] subclass.
 * Use the [ShiftListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShiftListFragment : Fragment() {
    lateinit var binding: FragmentShiftListBinding
    val viewModel: ShiftViewModel by activityViewModels()
    var shifts = MutableList<Data?>(0) { i -> null}
    var error = false
    var adapter: ShiftAdapter? = null
    private var START_DATE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shift_list, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getData(START_DATE)
        binding.swiperefresh.setOnRefreshListener {
            START_DATE++
            viewModel.getData(START_DATE)
        }
        viewModel.shiftDataLive.observe(viewLifecycleOwner){
            if(!it.isEmpty()) {
                adapter = ShiftAdapter(it[0].shifts, it[0].date)
                binding.swiperefresh.isRefreshing = false
                binding.recyclerView.adapter = adapter
            }
        }
        viewModel.errorLive.observe(viewLifecycleOwner){
            error = it
        }

    }
}