package com.fdmgroup.corona;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.repos.ShareRep;
@Service
public class SharesDAO {
	@Autowired
	private ShareRep repp;

	Optional<Share> share;
	List<Share> allShares;

	public void addShare(Share share) {
		share = repp.findById(share.getShareId());
		if (!share.isPresent()) {

			repp.save(share);
		}
	}

	public Share getShare(int shareId) {
		share = repp.findById(shareId);
		if (share.isPresent()) {

			return share.get();
		} else {
			System.out.print("No share whith this id therefore is ");
			return null;
		}
	}

	public void removeShare(int shareId) {
		share = repp.findById(shareId);

		if (share.isPresent()) {
			repp.delete(share.get());
			System.out.println("share removed");
		} else {
			System.out.println("No share under this id");
		}

	}

	public void updateShare(Share share) {

		share = repp.findById(share.getShareId());
		if (share.isPresent()) {
			repp.save(share.get());
			System.out.println("share changed");
		}
	}

	public List<Share> listShares() {
		allShares = repp.findAll();

		return allShares;
	}

	public SharesDAO() {

	}

	
}
