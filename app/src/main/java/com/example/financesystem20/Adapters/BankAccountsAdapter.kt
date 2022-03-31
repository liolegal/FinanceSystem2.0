package com.example.financesystem20.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.R

class BankAccountsAdapter(
        private val context: Context,
        private val dataSource: ArrayList<BankAccount>
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

        val rowView = inflater.inflate(R.layout.list_item_accounts, parent, false)
        val idTitle = rowView.findViewById(R.id.id_of_account_title) as TextView
        val moneyTitle = rowView.findViewById(R.id.money_title) as TextView

        val bankAccount = getItem(position) as BankAccount

        idTitle.text = bankAccount.idOfAccount
        moneyTitle.text = bankAccount.countOfMoney.toString()+"$"



        return rowView
    }
    fun removeItem(pos:Int,dbManager:BankAccountsDBManager){
        dbManager.deleteFromDb(dataSource[pos].id.toString())
        dataSource.removeAt(pos)
        notifyDataSetChanged()
    }

}