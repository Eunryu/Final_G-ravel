package com.example.g_ravel_tip;

import java.text.Collator;
import java.util.Comparator;

public class Event_ListData {

	public String ListTitle;
	public String ListStartDate;
	public String ListEndDate;
	
	//이름순 정렬
	public static final Comparator<Event_ListData> ALPHA_COMPARATOR = new Comparator<Event_ListData>() {
		
		private final Collator sCollator = Collator.getInstance();
		
		@Override
		public int compare(Event_ListData lhs, Event_ListData rhs) {
			// TODO Auto-generated method stub
			return sCollator.compare(lhs.ListTitle, rhs.ListTitle);
		}
	};
}
