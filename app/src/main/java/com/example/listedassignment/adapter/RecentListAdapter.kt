package com.example.listedassignment.adapter

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.listedassignment.R
import com.example.listedassignment.models.RecentLink
import com.example.listedassignment.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

class RecentListAdapter(private val recentLinksList: List<RecentLink>, private val ctx: Context?):
    RecyclerView.Adapter<RecentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_fragment_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recentLinksList.size
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = recentLinksList[position]

        GlideAdapter.setImage(holder.image, list.original_image)

        holder.url.text = list.web_link.trim()

        holder.title.text = list.title

        holder.date.text = Utils.getDate(list.created_at)

        holder.clicks.text = list.total_clicks.toString()

        holder.url.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(list.web_link)
            ctx?.startActivity(intent)
        }

        holder.copyText.setOnClickListener(View.OnClickListener {
            val clipboardManager = ctx?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", list.web_link)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(ctx, "link copied!", Toast.LENGTH_LONG).show()
        })
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val url: TextView = itemView.findViewById(R.id.url)
        val title: TextView = itemView.findViewById(R.id.title)
        val date: TextView = itemView.findViewById(R.id.date)
        val clicks: TextView = itemView.findViewById(R.id.clicks)
        val copyText: ImageView = itemView.findViewById(R.id.copyText)
    }
}