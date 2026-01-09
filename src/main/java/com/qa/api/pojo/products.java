package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class products {
		private Integer id;
		private String title;
		private Double price;
		private String description;
		private String category;
		private String image;
		private rating rating;

		
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		@Builder
		public static class rating{
		   private Number rate;
		   private long count;
		}
		
}
