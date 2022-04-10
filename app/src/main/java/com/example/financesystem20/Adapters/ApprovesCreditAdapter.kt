package com.example.financesystem20.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.financesystem20.Entities.Client
import com.example.financesystem20.Entities.Credit
import com.example.financesystem20.R

class ApprovesCreditAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Credit>
) : BaseAdapter() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        lateinit var toReturn: Any
        if (dataSource.isNotEmpty()) {
            toReturn = dataSource[position]
        }
        return toReturn
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        notifyDataSetChanged()
        val rowView = inflater.inflate(R.layout.list_item_approves_credit, parent, false)
        val clientTitle = rowView.findViewById(R.id.client_title) as TextView
        val id = rowView.findViewById(R.id.id_of_credit_approve) as TextView
        val percent = rowView.findViewById(R.id.percent_tv) as TextView
        val term = rowView.findViewById(R.id.term_title) as TextView
        val approve = getItem(position) as Credit

        clientTitle.text = approve.login
        id.text = approve.idOfCredit
        percent.text = approve.percent.toString()
        term.text = approve.creditTerm.toString()+"months"
            return rowView
    }


}