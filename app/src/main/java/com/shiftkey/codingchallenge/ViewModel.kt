package com.shiftkey.codingchallenge

import com.shiftkey.data.Data
import android.app.Application
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.databinding.ShiftItemBinding
import com.shiftkey.data.Shift
import kotlinx.coroutines.launch

class ShiftViewModel(app: Application) : AndroidViewModel(app)  {
    val shiftDataLive = MutableLiveData<List<Data>>()
    val errorLive = MutableLiveData<Boolean>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(start: Int) {
        val service = ShiftService.service
        viewModelScope.launch{
            val response = service.getShiftData(start = DateUtil(start), end= DateUtil(start+1))
            if (response.isSuccessful) {
                shiftDataLive.value = response.body()?.data
            } else {
                errorLive.value = true
            }
        }
    }
    fun getShift(i: Int): Shift? {
        val data = shiftDataLive.value
        return data?.get(0)?.shifts?.get(i)
    }
}

class ShiftAdapter(val data: List<Shift>, val date: String) : RecyclerView.Adapter<ShiftAdapter.ShiftViewHolder>(), ShiftClickListener {
    var index = 0
    class ShiftViewHolder(var v:ShiftItemBinding) : RecyclerView.ViewHolder(v.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiftViewHolder {
        val v = DataBindingUtil.inflate<ShiftItemBinding>(LayoutInflater.from(parent.context), R.layout.shift_item, parent, false)
        return ShiftViewHolder(v)
    }

    override fun onBindViewHolder(holder: ShiftViewHolder, position: Int) {
        val item = data[position]
        holder.v.date.text = date
        holder.v.mShiftId.text = item.shift_id.toString()
        holder.v.mShiftKind.text = item.shift_kind
        holder.v.mStartTime.text = item.start_time
        index=position
        holder.v.shiftClickListener = this
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onShiftItemClicked(v: View) {
        val action = ShiftListFragmentDirections.shiftListAction(index)
        //launch the fragment
        Navigation.findNavController(v).navigate(action)
    }
}
interface ShiftClickListener {
    fun onShiftItemClicked(v: View)
}