package com.example.financesystem20.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Entities.Client
import com.example.financesystem20.R

class ApprovesAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Client>
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

        val rowView = inflater.inflate(R.layout.list_item_approves, parent, false)
        val loginTitle = rowView.findViewById(R.id.client_login_tv) as TextView
        val name = rowView.findViewById(R.id.name_surname_tv) as TextView

        val approve = getItem(position) as Client

        loginTitle.text = approve.login
        name.text = approve.name+approve.surname



        return rowView
    }


}