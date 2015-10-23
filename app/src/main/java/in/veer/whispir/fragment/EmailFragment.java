package in.veer.whispir.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aabit.design.R;

import org.json.JSONException;
import org.json.JSONObject;

import in.veer.whispir.webutil.WebUtil;

/**
 * Created by aabit on 19/10/15.
 */
public class EmailFragment extends Fragment {

    Spinner spinner;
    String [] types={"text/plain", "text/html"};

    Button button;
    EditText edtTo, edtSub, edtBody;
    String data ="";

    public static Fragment newInstance(Context context) {
        EmailFragment f = new EmailFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_email, null);
        spinner = (Spinner) root.findViewById(R.id.spinner_type);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        edtTo = (EditText) root.findViewById(R.id.edt_to);
        edtSub = (EditText) root.findViewById(R.id.edt_subject);
        edtBody = (EditText) root.findViewById(R.id.edt_body);


        button = (Button) root.findViewById(R.id.btn_send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("to", edtTo.getText().toString());
                    jsonParam.put("subject", edtSub.getText().toString());
                    JSONObject jsonParam2 = new JSONObject();
                    jsonParam2.put("type", "text/html");
                    jsonParam2.put("body", edtBody.getText().toString());
                    jsonParam.put("email", jsonParam2);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                data = jsonParam.toString();

                new AsyncTask<Object, Object, String>(){

                    @Override
                    protected String doInBackground(Object... params) {
                        return WebUtil.sendData(data);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        Toast.makeText(getActivity(), "Response Message = " + s, Toast.LENGTH_LONG).show();
                    }
                }.execute((Object[]) null);
            }
        });

        return root;
    }
}
