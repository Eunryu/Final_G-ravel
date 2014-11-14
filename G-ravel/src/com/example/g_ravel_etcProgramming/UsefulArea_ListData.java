package com.example.g_ravel_etcProgramming;

import java.text.Collator;
import java.util.Comparator;

public class UsefulArea_ListData {
	public String Select;
	public String Area;
	public String Name;
	public String phone;
	public String address;

	// 이름순 정렬
	public static final Comparator<MyTravelRecord_ListData> ALPHA_COMPARATOR = new Comparator<MyTravelRecord_ListData>() {
		private final Collator sCollator = Collator.getInstance();

		@Override
		public int compare(MyTravelRecord_ListData mListDate_1,
				MyTravelRecord_ListData mListDate_2) {
			return sCollator.compare(mListDate_1.MyBigArea,
					mListDate_2.MyBigArea);
		}
	};
}
