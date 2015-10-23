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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.veer.whispir.webutil.WebUtil;

/**
 * Created by aabit on 19/10/15.
 */
public class WebFragment extends Fragment {
    Button button;
    EditText edtTo, edtSub, edtBody;
    String data ="";
    Spinner spinner;
    String [] types={"text/plain", "text/html"};

    public static Fragment newInstance(Context context) {
        WebFragment f = new WebFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_web, null);
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

                    jsonParam2.put("body", edtBody.getText().toString());
                    jsonParam2.put("type", "text/plain");

                    jsonParam.put("web", jsonParam2);

                    JSONObject jsonParam3 = new JSONObject();
                    JSONArray jsonArray = new JSONArray();

                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("id", "social");
                    jsonObject1.put("body", "");
                    JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("id", "socialType");
                    jsonObject2.put("body", "text/plain");
                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("id", "social_long");
                    jsonObject3.put("body", "");

                    jsonArray.put(jsonObject1);
                    jsonArray.put(jsonObject2);
                    jsonArray.put(jsonObject3);

                    jsonParam3.put("social", jsonArray);

                    jsonParam.put("social", jsonParam3);

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
