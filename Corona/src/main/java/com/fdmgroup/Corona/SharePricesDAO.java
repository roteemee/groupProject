package com.fdmgroup.Corona;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.repos.SharePriceRep;
@Service
public class SharePricesDAO {
	@Autowired
	private SharePriceRep repp;

	Optional<SharePrice> sharePrice;
	List<SharePrice> allSharePrices;

	public void addSharePrice(SharePrice sharePrice) {
		sharePrice = repp.findById(sharePrice.getSharePriceId());
		if (!sharePrice.isPresent()) {

			repp.save(sharePrice);
		}
	}

	public SharePrice getSharePrice(int sharePriceId) {
		sharePrice = repp.findById(sharePriceId);
		if (sharePrice.isPresent()) {

			return sharePrice.get();
		} else {
			System.out.print("No share price whith this id therefore is ");
			return null;
		}
	}

	public void removeSharePrice(int sharePriceId) {
		sharePrice = repp.findById(sharePriceId);

		if (sharePrice.isPresent()) {
			repp.delete(sharePrice.get());
			System.out.println("sharePrice removed");
		} else {
			System.out.println("No share price under this id");
		}

	}

	public void updateSharePrice(SharePrice sharePrice) {

		sharePrice = repp.findById(sharePrice.getSharePriceId());
		if (sharePrice.isPresent()) {
			repp.save(sharePrice.get());
			System.out.println("share price changed");
		}
	}

	public List<SharePrice> listSharePrices() {
		allSharePrices = repp.findAll();

		return allSharePrices;
	}

	public SharePricesDAO() {

	}

	
}
