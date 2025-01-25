package com.example.singup_app.presentation.ui.fragment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.singup_app.R
import com.example.singup_app.UserNotes

class LogicMyNotesAdapter(
    private val list: MutableList<UserNotes>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<LogicMyNotesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header: AppCompatTextView = itemView.findViewById(R.id.rvni_header)
        val message: AppCompatTextView = itemView.findViewById(R.id.rvni_message)
        val date: AppCompatTextView = itemView.findViewById(R.id.rvni_date)
        val deleteButton: AppCompatButton = itemView.findViewById(R.id.rvni_delet_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recicle_view_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.header.text = list[position].header
        holder.message.text = list[position].message
        holder.date.text = list[position].date
        holder.deleteButton.setOnClickListener {
            showPopupMenu(holder.deleteButton, position)
        }
    }

    private fun showPopupMenu(view: View, position: Int) {
        val popup = PopupMenu(view.context, view)
        popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_delete -> {
                    onDelete(position)
                    true
                }
                R.id.menu_share -> {
                    shareNote(view.context, position)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    private fun shareNote(context: Context, position: Int) {
        val note = list[position]
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Заметка: ${note.header}\n${note.message}\nДата: ${note.date}")
            type = "text/plain"
        }
        context.startActivity(Intent.createChooser(shareIntent, "Поделитесь заметкой"))
    }
}
