package com.example.financesystem20.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Entities.Credit
import com.example.financesystem20.R

class CreditsAdapter(
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

        val rowView = inflater.inflate(R.layout.list_item_credits, parent, false)
        val idTitle = rowView.findViewById(R.id.id_of_credit_title) as TextView
        val moneyTitle = rowView.findViewById(R.id.amount_of_debt_title) as TextView
        val termTitle = rowView.findViewById(R.id.term_title) as TextView

        val credit = getItem(position) as Credit

        idTitle.text = credit.idOfCredit
        moneyTitle.text = credit.amountOfDebt.toString() + "$"
        termTitle.text = credit.creditTerm.toString() + " months"



        return rowView
    }


}