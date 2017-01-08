package org.servialtura.contabilidad.base.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.SystemException;

import org.servialtura.contabilidad.base.db.GenericDAO;
import org.servialtura.contabilidad.base.model.Album;
import org.servialtura.contabilidad.base.model.FotoObra;
import org.servialtura.contabilidad.base.model.Obra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("obrasService")
@Transactional(readOnly = true)
public class ObrasService {

	/**
	 * 
	 */
	@Autowired
	GenericDAO<Obra, Serializable> genericDao;
	
	@Autowired
	GenericDAO<Album, Serializable> albumDao;
	
	@Autowired
	GenericDAO<FotoObra, Serializable> fotoDao;

	
	@Transactional(readOnly = true)
	public Obra getObra(int id){
		return genericDao.read(Obra.class,id);
	}
	
	@Transactional
	public List<Obra> getObras() throws SystemException{
		return genericDao.findAll(Obra.class);
	}
	
	
	@Transactional
	public void createObra(Obra pre) throws SystemException{
		genericDao.create(pre);
	}
	
	@Transactional
	public void updateObra(Obra pre) throws SystemException{
		genericDao.update(pre);
	}
	
	@Transactional
	public void deleteObra(Obra pre) throws SystemException{
		genericDao.delete(pre);
	}
	
	
	@Transactional(readOnly = true)
	public Album getalbum(int id){
		return albumDao.read(Obra.class,id);
	}
	
	@Transactional
	public void createAlbum(Album pre) throws SystemException{
		albumDao.create(pre);
	}
	
	@Transactional
	public void updateAlbum(Album pre) throws SystemException{
		albumDao.update(pre);
	}
	
	@Transactional
	public void createfoto(FotoObra pre) throws SystemException{
		fotoDao.create(pre);
	}
	
}
