package com.tiny.gasxm;

import java.util.ArrayList;
import java.util.HashMap;

import com.ximei.tiny.chaobiao.AlterBHActivity;
import com.ximei.tiny.chaobiao.BugHandleActivity;
import com.ximei.tiny.chaobiao.GroupCBFSActivity;
import com.ximei.tiny.chaobiao.SingleCBActivity;
import com.ximei.tiny.chaobiao.TargetBCActivity;
import com.ximei.tiny.chaobiao.TargetBCActivity01;
import com.ximei.tiny.chaobiao.YCbiaocelist;
import com.ximei.tiny.collector.CaiJiBiaoCeActivity;
import com.ximei.tiny.wlwmeter.MainWLWMeterActivity;
import com.ximei.tiny.wlwmeter.WLWBugHandleActivity;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

@TargetApi(11)
public class CBFragment extends Fragment {

//	private static final String[] chaobiaohint = { "单个抄表", "表册抄表", "采集数据","抄表统计", "强制关阀", "取消强关","改表地址","物联网表", "上传表册" };
//	private static final int[] imageid = { R.drawable.dgcb, R.drawable.bccb,
//			R.drawable.cjsj, R.drawable.cbxxtj,R.drawable.qzgf,R.drawable.qxqg,R.drawable.gbdz,R.drawable.cjzwbh,R.drawable.scbc };
	private static final String[] chaobiaohint = {"单个抄表","表册抄表", "采集数据","强制关阀", "取消强关","物联网表", "上传表册", "区域码"};
	private static final int[]    imageid = {R.drawable.dgcb,R.drawable.bccb,R.drawable.cjsj,R.drawable.qzgf,R.drawable.qxqg,R.drawable.cjzwbh,R.drawable.scbc,R.drawable.scbc};

	private Intent intent;
	private String overmsg;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View cbLayout = inflater.inflate(R.layout.cb, container, false);
		GridView localGridView = (GridView) cbLayout
				.findViewById(R.id.gridview);

		overmsg = getArguments().getString("overmsg");
		Log.e("test", overmsg);

		intent = getActivity().getIntent();

		// 用list<map>存放所用功能菜单作为数据源
		ArrayList<HashMap<String, Object>> localArrayList = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < chaobiaohint.length; i++) {

			HashMap<String, Object> localHashMap = new HashMap<String, Object>();
			localHashMap.put("ItemImage", imageid[i]);
			localHashMap.put("ItemText", chaobiaohint[i]);
			localArrayList.add(localHashMap);

		}
		// GridView设置数据源
		localGridView.setAdapter(new SimpleAdapter(cbLayout.getContext(),
				localArrayList, R.layout.gridview_meun, new String[] {
						"ItemImage", "ItemText" }, new int[] { R.id.ItemImage,
						R.id.ItemText }));

		// 设置点击事件
		localGridView.setOnItemClickListener(new ItemClickListener());

		return cbLayout;
	}

	// 点击相应的功能菜单进行相应的操作
	class ItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			String str = ((HashMap) arg0.getItemAtPosition(arg2)).get(
					"ItemText").toString();
			if (str.equals("单个抄表")) {

				intent.putExtra("overmsg", overmsg);
				//intent.putExtra("functoin", "singlecb");
				intent.setClass(getActivity(), SingleCBActivity.class);
				CBFragment.this.startActivity(intent);
			}
			if (str.equals("表册抄表")) {

				intent.putExtra("overmsg", overmsg);
				intent.setClass(getActivity(), GroupCBFSActivity.class);
				CBFragment.this.startActivity(intent);
			}
			if (str.equals("采集数据")) {

				intent.putExtra("caijitype", "caijidata");
				intent.putExtra("overmsg", overmsg);
				intent.setClass(getActivity(),CaiJiBiaoCeActivity.class);
				 CBFragment.this.startActivity(intent);
			}
			if (str.equals("抄表统计")) {
				intent.putExtra("cbtype", "infoview");
				intent.putExtra("overmsg", overmsg);
				intent.setClass(getActivity(), TargetBCActivity01.class);
				CBFragment.this.startActivity(intent);
			}
			if (str.equals("强制关阀")) {
				intent.putExtra("overmsg", overmsg);
				intent.putExtra("bugtype", "qcgf");
				intent.setClass(getActivity(),BugHandleActivity.class);
				CBFragment.this.startActivity(intent);
			}

			if (str.equals("取消强关")) {
				intent.putExtra("overmsg", overmsg);
				intent.putExtra("bugtype", "qxqg");
				intent.setClass(getActivity(),BugHandleActivity.class);
				CBFragment.this.startActivity(intent);
			}
			
			if (str.equals("物联网表")) {
				
				intent.setClass(getActivity(),WLWBugHandleActivity.class);
				CBFragment.this.startActivity(intent);
			}
			
			if (str.equals("上传表册")) {
//				intent.putExtra("overmsg", overmsg);
//				intent.putExtra("bugtype", "qxqg");
				intent.setClass(getActivity(),YCbiaocelist.class);
				CBFragment.this.startActivity(intent);
			}
			if (str.equals("区域码")) {
				intent.putExtra("overmsg",overmsg);
				intent.putExtra("shouhoutype", "qym");
				intent.setClass(getActivity(),AlterBHActivity.class);
				startActivity(intent);
			}
		}
	}

}
