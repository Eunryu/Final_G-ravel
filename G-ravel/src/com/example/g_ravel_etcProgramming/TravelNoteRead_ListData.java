package com.example.g_ravel_etcProgramming;

import java.text.Collator;
import java.util.Comparator;

public class TravelNoteRead_ListData {
	public String addTitle;
	public String addMesseage;
	
	// �̸��� ����
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
