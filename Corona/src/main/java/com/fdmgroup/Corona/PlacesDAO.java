package com.fdmgroup.Corona;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.repos.PlaceRep;
@Service
public class PlacesDAO {
	@Autowired
	private PlaceRep repp;

	Optional<Place> place;
	List<Place> allPlaces;

	public void addPlace(Place place) {
		place = repp.findById(place.getPlaceId());
		if (!place.isPresent()) {

			repp.save(place);
		}
	}

	public Place getPlace(int placeId) {
		place = repp.findById(placeId);
		if (place.isPresent()) {

			return place.get();
		} else {
			System.out.print("No place whith this id therefore is ");
			return null;
		}
	}

	public void removePlace(int placeId) {
		place = repp.findById(placeId);

		if (place.isPresent()) {
			repp.delete(place.get());
			System.out.println("place removed");
		} else {
			System.out.println("No place under this id");
		}

	}

	public void updatePlace(Place place) {

		place = repp.findById(place.getPlaceId());
		if (place.isPresent()) {
			repp.save(place.get());
			System.out.println("place changed");
		}
	}

	public List<Place> listPlaces() {
		allPlaces = repp.findAll();

		return allPlaces;
	}

	public PlacesDAO() {

	}

	
}
