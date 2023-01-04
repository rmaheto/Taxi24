package com.codemaniac.taxi.service;

import com.codemaniac.taxi.entity.Driver;
import com.codemaniac.taxi.entity.Rider;

import java.util.List;

public interface RiderService {
    public Rider addRider(Rider rider);
    public Rider getRider(Long riderId);
    public List<Rider> getAllRiders();
    public Rider updateRider(Long riderId, Rider rider);
    public void deleteRider(Long riderId);
}
