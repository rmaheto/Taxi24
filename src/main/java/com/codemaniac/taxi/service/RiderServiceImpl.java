package com.codemaniac.taxi.service;

import com.codemaniac.taxi.entity.Rider;
import com.codemaniac.taxi.exception.EntityNotFoundException;
import com.codemaniac.taxi.repository.RiderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {

    private RiderRepository riderRepository;


    public RiderServiceImpl(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @Override
    public Rider addRider(Rider rider) {
       return riderRepository.save(rider);
    }

    @Override
    public Rider getRider(Long riderId) {
        return riderRepository.findById(riderId)
                .orElseThrow(() -> new EntityNotFoundException(riderId));
    }

    @Override
    public List<Rider> getAllRiders() {
        return null;
    }

    @Override
    public Rider updateRider(Long riderId, Rider rider) {
        Rider updatedRider = riderRepository.findById(riderId)
                .orElseThrow(() -> new EntityNotFoundException(riderId));

        updatedRider.setName(rider.getName());
        updatedRider.setGender(rider.getGender());
        updatedRider.setPhoneNumber(rider.getPhoneNumber());
        riderRepository.save(updatedRider);
        return updatedRider;
    }

    @Override
    public void deleteRider(Long riderId) {
        riderRepository.deleteById(riderId);

    }
}
