package com.omrbranch.pojo.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Astate {
	 public int id;
	    public String name;
	    public int country_id;
	    public String status;
	    public Acountry country;
}
