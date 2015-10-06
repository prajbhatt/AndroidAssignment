package gitassignment.com.mygitassignment;

/**
 * Created by admin on 10/5/2015.
 */

import android.app.Activity;
import android.content.Context;

import android.content.res.Resources;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.impl.conn.tsccm.RouteSpecificPool;

import java.util.ArrayList;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class ListAdapter extends BaseAdapter {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;

    ResponseBean responseBean;

    int i=0;

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    /*************  CustomAdapter Constructor *****************/
    public ListAdapter(Activity a, ArrayList d) {

        /********** Take passed values **********/
        activity = a;
        data=d;


        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView text;
        public TextView text1;
        public TextView text2;


    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.item, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.autherName);
           holder.text1=(TextView)vi.findViewById(R.id.commetTxt);
           holder.text2=(TextView)vi.findViewById(R.id.commentDate);
            //holder.image=(ImageView)vi.findViewById(R.id.image);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.text.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/

            responseBean =null;
            responseBean = ( ResponseBean ) data.get( position );

            holder.text.setText( responseBean.getCommitterNAme() );
            holder.text1.setText(Html.fromHtml("<b>Message: </b>")+responseBean.getMessage());
            System.out.println("responseBean.getDate()"+responseBean.getDate());
            holder.text2.setText(Html.fromHtml("<b>Commit: </b>")+responseBean.getDate());

        }
        return vi;
    }

}