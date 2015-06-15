package surveypark.service;

import surveypark.domain.User;

public interface UserService extends BaseService<User> {
/**
 * 邮箱校验
 */
	public boolean validateEmail(String email);

public User validateEmailAndPwd(String email, String password);
}
