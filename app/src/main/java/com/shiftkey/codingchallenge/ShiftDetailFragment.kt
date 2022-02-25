package com.shiftkey.codingchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.shiftkey.codingchallenge.databinding.FragmentShiftDetailBinding
import com.shiftkey.data.Shift

/**
 * A simple [Fragment] subclass.
 * Use the [ShiftDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShiftDetailFragment : Fragment() {

    val viewModel:ShiftViewModel by activityViewModels()
    var shiftIdx = 0
    val SHIFT_IDX = "SHIFT_IDX"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shiftIdx = it.getInt(SHIFT_IDX)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            val binding:FragmentShiftDetailBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shift_detail, container, false)
            binding.shiftItem = viewModel.getShift(shiftIdx)
            return binding.root
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShiftDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int) =
            ShiftDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(SHIFT_IDX, param1)
                }
            }
    }
}