package edu.eci.dosw.tdd.service;

import edu.eci.dosw.tdd.exception.ResourceNotFoundException;
import edu.eci.dosw.tdd.model.User;
import edu.eci.dosw.tdd.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

	private final Map<String, User> usersById = new HashMap<>();

	public User addUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("user is required");
		}

		String id = ValidationUtil.requireNonBlank(user.getId(), "user.id");
		ValidationUtil.requireNonBlank(user.getName(), "user.name");
		ValidationUtil.requireNonBlank(user.getEmail(), "user.email");

		usersById.put(id, user);
		return user;
	}

	public List<User> getAllUsers() {
		return new ArrayList<>(usersById.values());
	}

	public User getUserById(String userId) {
		ValidationUtil.requireNonBlank(userId, "userId");
		User user = usersById.get(userId);
		if (user == null) {
			throw new ResourceNotFoundException("User not found: " + userId);
		}
		return user;
	}
}
