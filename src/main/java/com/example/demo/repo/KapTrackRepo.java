package com.example.demo.repo;

import com.example.demo.modal.KapTrackModal;

public interface KapTrackRepo {
    boolean saveOrUpdate(KapTrackModal modal);
}
