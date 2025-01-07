package com.example.singup_app.presentation.ui.fragment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.singup_app.R
import com.example.singup_app.UserNotes
import com.example.singup_app.databinding.RecicleViewItemBinding // Импортируйте класс биндинга

class LogicMyNotesAdapter(
    private val list: MutableList<UserNotes>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<LogicMyNotesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: RecicleViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: UserNotes, list: MutableList<UserNotes>, onDelete: (Int) -> Unit, position: Int) {
            binding.rvniHeader.text = note.header
            binding.rvniMessage.text = note.message
            binding.rvniDate.text = note.date

            binding.rvniDeletButton.setOnClickListener {
                showPopupMenu(binding.rvniDeletButton, position, list, onDelete)
            }
        }

        private fun showPopupMenu(view: View, position: Int, list: MutableList<UserNotes>, onDelete: (Int) -> Unit) {
            val popup = PopupMenu(view.context, view)
            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_delete -> {
                        onDelete(position)
                        true
                    }
                    R.id.menu_share -> {
                        shareNote(view.context, position, list)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        private fun shareNote(context: Context, position: Int, list: MutableList<UserNotes>) {
            val note = list[position]
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Заметка: ${note.header}\n${note.message}\nДата: ${note.date}")
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(shareIntent, "Поделитесь заметкой"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecicleViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], list, onDelete, position)
    }
}