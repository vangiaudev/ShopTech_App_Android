package vangiau.example.shoptech.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vangiau.example.shoptech.R;
import vangiau.example.shoptech.activity.DienThoaiActivity;
import vangiau.example.shoptech.activity.DongHoActivity;
import vangiau.example.shoptech.activity.MacbookActivity;
import vangiau.example.shoptech.activity.TabletActivity;
import vangiau.example.shoptech.activity.TinTucActivity;
import vangiau.example.shoptech.general.CheckConnect;
import vangiau.example.shoptech.general.Server;
import vangiau.example.shoptech.model.DanhMuc;
import vangiau.example.shoptech.model.ItemClickListener;
import vangiau.example.shoptech.model.LoaiThietBi;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{
    public TextView txtTenDanhMuc;
    public CircleImageView cimgHinhDanhMuc;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        txtTenDanhMuc = itemView.findViewById(R.id.txtTenDanhMuc);
        cimgHinhDanhMuc = itemView.findViewById(R.id.cimgDanhMuc);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
public class DanhMucAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<DanhMuc> arrDanhMuc = new ArrayList<>();
    private Context context;

    int id                = 0;
    String tenLoaiThietBi = "";
    String hinhAnhLoai    = "";

    private ArrayList<LoaiThietBi>  arrayLoaiThietBi = new ArrayList<>();
    private LoaiThietBiAdapter      adapterLoai = new LoaiThietBiAdapter(arrayLoaiThietBi, context);


    public DanhMucAdapter(List<DanhMuc> listData, Context context) {
        this.arrDanhMuc = listData;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_danh_muc_sp,parent,false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        DanhMuc danhMuc = arrDanhMuc.get(position);
        holder.txtTenDanhMuc.setText(danhMuc.getTen());
        holder.cimgHinhDanhMuc.setImageResource(danhMuc.getHinh());
        GetDataLoai();

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
               switch (danhMuc.getTen()){
                   case "Điện thoại":
                       context.startActivity(new Intent(context, DienThoaiActivity.class)
                               .putExtra("idloaithietbi", arrayLoaiThietBi.get(0).getId()));
                       break;
                   case "Macbook":
                       context.startActivity(new Intent(context, MacbookActivity.class)
                               .putExtra("idloaithietbi", arrayLoaiThietBi.get(1).getId()));
                       break;
                   case "Tablet":
                       context.startActivity(new Intent(context, TabletActivity.class)
                               .putExtra("idloaithietbi", arrayLoaiThietBi.get(2).getId()));
                       break;
                   case "Đồng hồ":
                       context.startActivity(new Intent(context, DongHoActivity.class)
                               .putExtra("idloaithietbi", arrayLoaiThietBi.get(2).getId()));
                       break;
                   case "Khuyến mãi":
                       context.startActivity(new Intent(context, TinTucActivity.class));
                       break;
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrDanhMuc.size();
    }

    private void GetDataLoai() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathLoaiThietBi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){

                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id                    = jsonObject.getInt("id");
                            tenLoaiThietBi        = jsonObject.getString("ten");
                            hinhAnhLoai           = jsonObject.getString("hinhanh");

                            arrayLoaiThietBi.add(new LoaiThietBi(id, tenLoaiThietBi, hinhAnhLoai));
                            adapterLoai.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ToastMess(context, error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
