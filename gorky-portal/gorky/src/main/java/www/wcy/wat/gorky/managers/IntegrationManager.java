package www.wcy.wat.gorky.managers;

import java.io.CharConversionException;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import www.wcy.wat.gorky.dto.IntegrationDTO;
import www.wcy.wat.gorky.model.Avatar;
import www.wcy.wat.gorky.model.Characterattributes;
import www.wcy.wat.gorky.model.User;
import www.wcy.wat.gorky.repositories.AvatarRepository;
import www.wcy.wat.gorky.repositories.CharacterAttributesRepository;
import www.wcy.wat.gorky.repositories.UserRepository;

@Component
public class IntegrationManager {

	@Autowired
	AvatarRepository avatarRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CharacterAttributesRepository characterAttributesRepository;

	public IntegrationDTO createJSonModelObjectForUsrerId(Integer userId) {
		User user = userRepository.findUserById(userId);
		Avatar avatar = avatarRepository.findAvatarByUserId(userId);
		Integer avatarId = avatar.getIdavatar();
		Characterattributes characterattributes = characterAttributesRepository
				.findCharacterattributesByAvatarId(1);
		return this.createDTOFromEnitiesMapper(user, avatar,
				characterattributes);
	}

	public IntegrationDTO createDTOFromEnitiesMapper(User user, Avatar avatar,
			Characterattributes characterattributes) {

		IntegrationDTO integrationDTO = new IntegrationDTO();

		// inetgartion user details
		if (user != null) {
			integrationDTO.setCity(user.getCity());
			integrationDTO.setCountry(user.getCountry());
			integrationDTO.setDescription(user.getDescription());
			integrationDTO.setEmail(user.getEmail());
			integrationDTO.setIduser(user.getIduser());
			integrationDTO.setLogin(user.getLogin());
			integrationDTO.setNameUser(user.getName());
			integrationDTO.setOld(user.getOld());
			integrationDTO.setPassword(user.getPassword());
			integrationDTO.setSurname(user.getSurname());
		}

		if (avatar != null) {
			// inetgration avatar details
			integrationDTO.setCurrentMapLevel(avatar.getCurrentMapLevel());
			integrationDTO.setIdavatar(avatar.getIdavatar());
			integrationDTO.setNameAvatar(avatar.getName());
			integrationDTO.setPositionX(avatar.getPositionX());
			integrationDTO.setPositionY(avatar.getPositionY());
		}

		if (characterattributes != null) {
			// inegration character attributes
			integrationDTO.setAtk(characterattributes.getAtk());
			integrationDTO.setDef(characterattributes.getDef());
			integrationDTO.setExp(characterattributes.getExp());
			integrationDTO.setGold(characterattributes.getGold());
			integrationDTO.setHp(characterattributes.getHp());
			integrationDTO.setIdcharacterAttribute(characterattributes
					.getIdcharacterAttribute());
			integrationDTO.setLevel(characterattributes.getLevel());
			integrationDTO.setMagAtk(characterattributes.getMagAtk());
			integrationDTO.setMagDef(characterattributes.getMagDef());
			integrationDTO.setMaxHP(characterattributes.getMaxHP());
			integrationDTO.setMaxMP(characterattributes.getMaxMP());
			integrationDTO.setMp(characterattributes.getMp());
		}

		return integrationDTO;

	}

}
