package com.mobile.food.facility.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {
	private int currentPage;
	private int perPage;
	private int totalpages;
	private long totalObjects;
}
