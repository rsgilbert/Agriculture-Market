package com.gilboot.agriculturemarket

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.gilboot.agriculturemarket.models.Produce
import com.gilboot.agriculturemarket.produce.ProduceListAdapter

@BindingAdapter("addDivider")
fun RecyclerView.addDivider(shouldAdd: Boolean?) =
    shouldAdd?.let {
        if (it) {
            val itemDec = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDec.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
            addItemDecoration(itemDec)
        }
    }

@BindingAdapter("addMaterialDivider")
fun RecyclerView.bindMaterialDivider(shouldAdd: Boolean?) =
    shouldAdd?.let {
        if (it) {
            val itemDec = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDec.setDrawable(ContextCompat.getDrawable(context, R.drawable.material_divider)!!)
            addItemDecoration(itemDec)
        }
    }

// Bind picture list to recyclerview
// using the picture adapter
@BindingAdapter("produceList")
fun RecyclerView.bindProduces(produces: List<Produce>?) {
    produces?.let {
        (adapter as ProduceListAdapter).submitList(produces)
    }
}
