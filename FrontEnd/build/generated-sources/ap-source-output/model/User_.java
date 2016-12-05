package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile ListAttribute<User, Meddelandet> meddelandetList;
	public static volatile SingularAttribute<User, String> password;
	public static volatile ListAttribute<User, Meddelandet> meddelandetList1;
	public static volatile SingularAttribute<User, String> name;
	public static volatile ListAttribute<User, Log> logList;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile ListAttribute<User, Meddelandet> chatList1;
	public static volatile SingularAttribute<User, String> confirmpassword;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> username;
	public static volatile ListAttribute<User, Meddelandet> chatList;

}

