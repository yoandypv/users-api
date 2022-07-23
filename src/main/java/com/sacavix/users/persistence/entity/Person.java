package com.sacavix.users.persistence.entity;

import javax.persistence.*;

import com.sacavix.users.persistence.projections.PersonLocationDTO2;
import lombok.Data;

// @NamedNativeQuery && @SqlResultSetMapping is only required for named query

@Data
@Entity
@NamedNativeQuery(
		name = "getPersonLocationDTONamingQuery",
		query =
				"SELECT " +
						" P.NAME as name," +
						" P.PHONE_NUMBER AS phoneNumber, " +
						" A.STREET AS street " +
						" FROM PERSON P " +
						" INNER JOIN ADDRESS A ON A.ID = P.ADDRESS_ID " +
						" WHERE P.ID = :id",
		resultSetMapping = "PersonLocationDTO2Mapping"
)
@SqlResultSetMapping(
		name = "PersonLocationDTO2Mapping",
		classes = @ConstructorResult(
				targetClass = PersonLocationDTO2.class,
				columns = {
						@ColumnResult(name = "name", type = String.class),
						@ColumnResult(name = "phone_number", type = String.class),
						@ColumnResult(name = "street", type = String.class)
				}
		)
)
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String phoneNumber;
	private String email;
	
	private long addressId;
}
