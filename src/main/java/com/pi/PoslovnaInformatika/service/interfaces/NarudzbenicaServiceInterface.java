package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pi.PoslovnaInformatika.dto.NarudzbenicaComboDTO;
import com.pi.PoslovnaInformatika.dto.NarudzbenicaDTO;
import com.pi.PoslovnaInformatika.model.Narudzbenica;

public interface NarudzbenicaServiceInterface {

	public Narudzbenica getOne(Integer id);
	
	public Page<Narudzbenica> findAll(Pageable pageRequest);

	public Narudzbenica save(Narudzbenica narudzbenica);

	public List<Narudzbenica> save(List<Narudzbenica> narudzbenice);

	public void delete(Integer id);
	
	public void delete(List<Integer> ids);
	
	public Narudzbenica edit(NarudzbenicaDTO narudzbenica,Integer id);
	
	public void softDelete(Integer id);
	
	public Narudzbenica saveCombo(NarudzbenicaComboDTO comboDTO);
	
}
