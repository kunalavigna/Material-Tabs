package in.veer.whispir.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.aabit.design.R;

/**
 * Created by aabit on 19/10/15.
 */
public class NoLayoutFragment extends Fragment {

    Spinner spinner;

    public static Fragment newInstance(Context context) {
        NoLayoutFragment f = new NoLayoutFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_no_lay, null);

        return root;
    }
}
