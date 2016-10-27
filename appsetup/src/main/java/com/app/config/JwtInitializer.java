package com.app.config;
import com.app.server.repository.appbasicsetup.aaa.JwtConfigRepository;

import com.app.shared.appbasicsetup.aaa.JwtConfig;

public class JwtInitializer {

	private JwtConfigRepository<JwtConfig> jWTConfigRepo;

	public JwtInitializer(JwtConfigRepository<JwtConfig> astJWTConfigRepository) {
		this.jWTConfigRepo = astJWTConfigRepository;
	}

	public String getKey() throws Exception {

		try {
			final JwtConfig astJWTConfig = jWTConfigRepo.findAll().get(0);
			return astJWTConfig.getTokenKey();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
}
