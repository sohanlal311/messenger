package com.sohan.restfulapi.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sohan.restfulapi.messenger.database.DatabaseCache;
import com.sohan.restfulapi.messenger.model.Profile;

public class ProfileService {

	private static Map<String, Profile> profiles = DatabaseCache.getProfiles();

	static {
		Profile p1 = new Profile(1, "sohanlal311", "Sohan", "Lal", new Date());
		Profile p2 = new Profile(2, "sonujalap", "Sonu", "Jalap", new Date());
		profiles.put(p1.getProfileName(), p1);
		profiles.put(p2.getProfileName(), p2);
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfileByName(String profileName) {
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getId() <= 0) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public void deleteProfile(String profileName) {
		profiles.remove(profileName);
	}

}
