package ie.cm.fragments;

import ie.cm.activities.Base;
import ie.cm.activities.Edit;
import ie.cm.adapters.CoffeeListAdapter;
import ie.cm.models.Coffee;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class CoffeeFragment  extends ListFragment implements  OnClickListener
{
  protected         Base                activity;
  protected static  CoffeeListAdapter 	listAdapter;
  protected         ListView 			listView;

  public CoffeeFragment() {
    // Required empty public constructor
  }

  public static CoffeeFragment newInstance() {

    CoffeeFragment fragment = new CoffeeFragment();

    return fragment;
  }

  public void onCoffeeDelete(final Coffee coffee) {
    String stringName = coffee.name;
    AlertDialog.Builder builder = new AlertDialog.Builder(activity); builder.setMessage("Are you sure you want to Delete the \'Coffee\' " +
            stringName + "?"); builder.setCancelable(false);
    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        Base.coffeeList.remove(coffee); // remove from our list
         listAdapter.coffeeList.remove(coffee); // update adapters data
         listAdapter.notifyDataSetChanged(); // refresh adapter
      }
    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      } });
    AlertDialog alert = builder.create();
    alert.show();
  }
  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    Bundle activityInfo = new Bundle(); // Creates a new Bundle object
    activityInfo.putInt("coffeeID", (int)id+1); /* pass in the coffees id here */
    Intent goEdit = new Intent(getActivity(), Edit.class);
    goEdit.putExtras(activityInfo);// Creates a new Intent /* Add the bundle to the intent here */
    getActivity().startActivity(goEdit);
  }
@Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    this.activity = (Base) context;
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    listAdapter = new CoffeeListAdapter(activity, this, Base.coffeeList);
    setListAdapter (listAdapter);
    super.onCreate(savedInstanceState);

  }
     
  @Override
  public void onStart()
  {
    super.onStart();
  }

  @Override
  public void onClick(View view) {
    if (view.getTag() instanceof Coffee) {
      onCoffeeDelete ((Coffee) view.getTag()); }
  }


}

  