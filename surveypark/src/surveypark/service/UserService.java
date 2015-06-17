package surveypark.service;

import surveypark.domain.User;

public interface UserService extends BaseService<User> {
/**
 * 邮箱校验
 */
	public boolean validateEmail(String email);

public User validateEmailAndPwd(String email, String password);

public void updateAuthorize(User model, Integer[] ownRoleIds);
public void clearAuthorization(Integer userid);
}
