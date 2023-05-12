package com.shinhan.education.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode*/

@Data
@Builder  //default contructor를 제공하지 않는다. 
@AllArgsConstructor
@NoArgsConstructor
public class CarVO {
	private String model;
	private int price;
}
