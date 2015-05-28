package cn.spring.jdbc.transaction;

public class PersonServiceImpl implements PersonService{
    private PersonDao persondao;
	
	public PersonDao getPersondao() {
		return persondao;
	}

	public void setPersondao(PersonDao persondao) {
		this.persondao = persondao;
	}

	public void savePerson() {
		 this.persondao.savePerson();
	}

}
