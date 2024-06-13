package sns.example.suitmediatest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sns.example.suitmediatest.databinding.ItemUserBinding
import sns.example.suitmediatest.response.DataItem

class UserAdapter(private val onClick: (DataItem) -> Unit) :
    PagingDataAdapter<DataItem, UserAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            "${data.firstName} ${data.lastName}".also { holder.binding.tvFullName.text = it }
            holder.binding.tvEmail.text = data.email
            Glide.with(holder.itemView)
                .load(data.avatar)
                .into(holder.binding.imgUser)
            holder.itemView.setOnClickListener {
                onClick(data)
            }
        }
    }

    class ItemViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}