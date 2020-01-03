package br.com.edriven.edittextview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;

import com.google.android.material.textfield.TextInputEditText;

public class CepEditText extends TextInputEditText {

    public CepEditText(Context context) {
        super(context);
        init();
    }

    public CepEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CepEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        setMaxLines(1);
        setSingleLine();
        setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        addTextChangedListener(textWatcher);
    }


    TextWatcher textWatcher = new TextWatcher() {

        boolean isUpdate = false;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isUpdate) {
                isUpdate = false;
                return;
            }
            isUpdate = true;
            String current = s.toString().replace(".", "").replace("-", "");
            StringBuilder finalText = new StringBuilder();
            int contString = 0;
            final String format = "00.000-000";
            for (int cont = 0; cont < format.length(); cont++) {
                if (current.length() == 0)
                    return;
                if (format.toCharArray()[cont] == '0') {
                    finalText.append(current.toCharArray()[contString]);
                    contString++;
                } else {
                    finalText.append(format.toCharArray()[cont]);
                }
                if (contString == current.length())
                    break;
            }
            setText(finalText);
            setSelection(finalText.length());
            isUpdate = false;
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
