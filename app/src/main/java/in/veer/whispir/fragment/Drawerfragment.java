package in.veer.whispir.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aabit.design.R;
import in.veer.whispir.adapter.DrawerAdapter;


public class Drawerfragment extends Fragment {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    ListView listView;
    String arr [];

    final String[] fragments ={
            "in.veer.whispir.fragment.SMSFragment",
            "in.veer.whispir.fragment.EmailFragment",
            "in.veer.whispir.fragment.VoiceFragment",
            "in.veer.whispir.fragment.NoLayoutFragment",
            "in.veer.whispir.fragment.RichMsgFragment",
            "in.veer.whispir.fragment.NoLayoutFragment",
            "in.veer.whispir.fragment.WebFragment",
            "in.veer.whispir.fragment.NoLayoutFragment"};


    public Drawerfragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr = getResources().getStringArray(R.array.list_data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        listView = (ListView) view.findViewById(R.id.list_view);


        DrawerAdapter adapter = new DrawerAdapter(getActivity(),arr);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("List item = ", "" + position);
            }
        });

        return view;
    }

    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar,R.string.drawer_opened,R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActivity().invalidateOptionsMenu();
            }


            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //getActivity().invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos, long id) {
                mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                        tx.replace(R.id.main, Fragment.instantiate(getActivity(), fragments[pos]));
                        tx.commit();
                    }
                });
                mDrawerLayout.closeDrawers();
            }
        });
        FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.main, Fragment.instantiate(getActivity(), fragments[0]));
        tx.commit();

    }
}
