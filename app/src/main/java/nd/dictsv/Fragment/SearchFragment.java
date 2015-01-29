package nd.dictsv.Fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;

import nd.dictsv.Adaptor.CustomAdapter2;
import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.DAO.Word;
import nd.dictsv.DAO.WordDAO;
import nd.dictsv.Debug.Message;
import nd.dictsv.R;
import nd.dictsv.SearchingTask;


/**
 * Created by ND on 9/7/2557.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    private View rootView;
    EditText edt_search;
    ListView listViewSearch;

    private List<Word> words;
    HashMap<String, Word> wordHashMap;
    HashMap<Long, Word> wordHashMapLong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container, false);

        //view matching
        listViewSearch = (ListView) rootView.findViewById(R.id.search_listView);
        edt_search = (EditText) rootView.findViewById(R.id.edt_search);

        //search by catID (0 : All category)
        WordDAO wordDAO = new WordDAO(getActivity());
        //words = wordDAO.getAllWord();
        //TODO john
        wordHashMap = wordDAO.getAllWordHashMap();
        wordHashMapLong = wordDAO.getAllWordHashMapLong();


        if (wordHashMapLong.isEmpty()) Message.shortToast(getActivity(), TAG, "Empty");
        CustomAdapter2 customAdapter2 = new CustomAdapter2(getActivity(), wordHashMapLong,
                new CategoryDAO(getActivity()).getAllCategoryHashmap());
        listViewSearch.setAdapter(customAdapter2);

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Toast.makeText(getActivity(),"baforeTextChange",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Toast.makeText(getActivity(),"onTextChange",Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                Message.toast2(getActivity(), "afterTextChanged");

                String inputText = edt_search.getText().toString();
                if (inputText.length()==0) {
                    listViewSearch.setAdapter(null);
                } else {

                    SearchingTask searchingTask = new SearchingTask(getActivity(), listViewSearch,
                            inputText, wordHashMapLong);
                    searchingTask.execute();
                }

            }
        });


        return rootView;
    }
}
