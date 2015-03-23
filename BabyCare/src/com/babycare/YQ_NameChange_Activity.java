package com.babycare;

/**
 * 
 */

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.babycare.base.CustomTitleActivity;

/**
 * 
 * @Description: TODO()
 * @author 易勤
 * @date 2014-11-10 上午9:51:10
 * @version V1.0
 */
public class YQ_NameChange_Activity extends CustomTitleActivity {
	private EditText name;
	private ImageView iv_delete;
	private TextView text_tip;
	private final static int MAX_COUNT = 15;

	/*
	 * (重写父类方法)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_namechange);
		super.onCreate(savedInstanceState);
		setTitle("姓名");
		setTitleBarRightBtnText("完成");

	}
	
	@Override
	public void onTitleBarRightBtnClick(View view) {
		finish();
	}
	

	private TextWatcher mTextWatcher = new TextWatcher() {

		private int editStart;

		private int editEnd;

		public void afterTextChanged(Editable s) {
			editStart = name.getSelectionStart();
			editEnd = name.getSelectionEnd();

			// 先去掉监听器，否则会出现栈溢出
			name.removeTextChangedListener(mTextWatcher);

			// 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
			// 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
			while (calculateLength(s.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作
				s.delete(editStart - 1, editEnd);
				editStart--;
				editEnd--;
			}
			// mEditText.setText(s);将这行代码注释掉就不会出现后面所说的输入法在数字界面自动跳转回主界面的问题了，多谢@ainiyidiandian的提醒
			name.setSelection(editStart);

			// 恢复监听器
			name.addTextChangedListener(mTextWatcher);

		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			String text = s.toString();
			if (text.trim().equals("")) {
				text_tip.setVisibility(View.VISIBLE);
			} else {
				text_tip.setVisibility(View.GONE);
			}
		}

	};

	/**
	 * 计算分享内容的字数，一个汉字=两个英文字母，一个中文标点=两个英文标点 注意：该函数的不适用于对单个字符进行计算，因为单个字符四舍五入后都是1
	 * 
	 * @param c
	 * @return
	 */
	private long calculateLength(CharSequence c) {
		double len = 0;
		for (int i = 0; i < c.length(); i++) {
			int tmp = (int) c.charAt(i);
			if (tmp > 0 && tmp < 127) {
				len += 0.5;
			} else {
				len++;
			}
		}
		return Math.round(len);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		text_tip = (TextView) findViewById(R.id.text_tip);

		iv_delete = (ImageView) findViewById(R.id.iv_delete);
		iv_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				name.setText("");
			}
		});
		name = (EditText) findViewById(R.id.et_address);

		name.addTextChangedListener(mTextWatcher);
		name.setSelection(name.length()); // 将光标移动最后一个字符后面

	}

	@Override
	protected void initDatas() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void installListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void uninstallListeners() {
		// TODO Auto-generated method stub

	}

}
