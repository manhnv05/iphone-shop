package com.example.mobile_shop.service.san_pham;

import com.example.mobile_shop.dto.san_pham.BoNhoTrongDTO;
import com.example.mobile_shop.dto.san_pham.ChiSoKhangBuiVaNuocDTO;
import com.example.mobile_shop.entity.SanPham.BoNhoTrong;
import com.example.mobile_shop.repository.san_pham.BoNhoTrongRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoNhoTrongService {

    private final BoNhoTrongRepository boNhoTrongRepository;

    public BoNhoTrongService(BoNhoTrongRepository boNhoTrongRepository) {
        this.boNhoTrongRepository = boNhoTrongRepository;
    }

    public Page<BoNhoTrongDTO> getAllBoNhoTrongs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return boNhoTrongRepository.findByDeletedFalse(pageable).map(this::toDTO);
    }

    public List<BoNhoTrongDTO> getAllBoNhoTrongsList() {
        return boNhoTrongRepository.findByDeletedFalse().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public BoNhoTrongDTO addBoNhoTrong(BoNhoTrongDTO boNhoTrongDTO) {
        BoNhoTrong boNhoTrong = new BoNhoTrong();
        boNhoTrong.setMa(boNhoTrongDTO.getMa());
        boNhoTrong.setDungLuongBoNhoTrong(boNhoTrongDTO.getDungLuong());
        boNhoTrong.setDeleted(false);
        BoNhoTrong savedBoNhoTrong = boNhoTrongRepository.save(boNhoTrong);
        return toDTO(savedBoNhoTrong);
    }

    private BoNhoTrongDTO toDTO(BoNhoTrong entity) {
        return new BoNhoTrongDTO(entity.getId(), entity.getMa(), entity.getDungLuongBoNhoTrong());
    }
}