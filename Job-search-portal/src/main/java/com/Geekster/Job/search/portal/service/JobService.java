package com.Geekster.Job.search.portal.service;

import com.Geekster.Job.search.portal.model.Job;
import com.Geekster.Job.search.portal.repository.JobRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    public List<Job> findAllJobs() {
      return jobRepository.findAll();
    }

    public ResponseEntity<String> updateJob(Long id) {
        Job newJob = new Job();
        if(jobRepository.existsById(id)){
            Job job= jobRepository.findById(id).get();
            newJob.setId(job.getId());
            newJob.setJobType(job.getJobType());
            newJob.setDescription(job.getDescription());
            newJob.setLocation(job.getLocation());
            newJob.setTitle(job.getTitle());
            newJob.setSalary(job.getSalary());
            newJob.setCompanyEmail(job.getCompanyEmail());
            return new ResponseEntity<>("job has been updated", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Job has been not found",HttpStatus.NOT_FOUND);
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public Optional<Job> updateJob(Long id, Job job) {
        Optional<Job> existingJobOptional = jobRepository.findById(id);
        if (existingJobOptional.isPresent()) {
            Job existingJob = existingJobOptional.get();
            existingJob.setTitle(job.getTitle());
            existingJob.setDescription(job.getDescription());
            existingJob.setLocation(job.getLocation());
            existingJob.setSalary(job.getSalary());
            existingJob.setCompanyEmail(job.getCompanyEmail());
            existingJob.setCompanyName(job.getCompanyName());
            existingJob.setEmployerName(job.getEmployerName());
            existingJob.setJobType(job.getJobType());
            existingJob.setAppliedDate(job.getAppliedDate());
            return Optional.of(jobRepository.save(existingJob));
        }
        return Optional.empty();
    }
    public boolean deleteJob(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Job> searchJobsByTitleAndDescription(String title, String description) {
        return jobRepository.findByTitleContainingAndDescriptionContaining(title, description);
    }
    public List<Job> findByTitleContaining(String keyword) {
        return jobRepository.findByTitleContaining(keyword);
    }
    public List<Job> findByDescriptionContaining(String keyword) {
        return jobRepository.findByDescriptionContaining(keyword);
    }
    public List<Job> findByLocation(String location) {
        return jobRepository.findByLocation(location);
    }
    public List<Job> findByCompanyName(String companyName) {
        return jobRepository.findByCompanyName(companyName);
    }
    @Transactional
    public boolean updateJobTitle(Long id, String title) {
        jobRepository.updateJobTitle(id, title);
        return jobRepository.existsById(id);
    }
    @Transactional
    public boolean deleteByEmployerName(String employerName) {
        jobRepository.deleteByEmployerName(employerName);
        return true;
    }
    public List<Job> findByCompanyEmailDomain(String domain) {
        return jobRepository.findByCompanyEmailDomain(domain);
    }
    public List<Job> findByDescriptionContainingKeywords(String keyword1, String keyword2) {
        return jobRepository.findByDescriptionContainingKeywords(keyword1, keyword2);
    }
    public List<Job> findByTitleContainingAndDescriptionContaining(String title, String description) {
        return jobRepository.findByTitleContainingAndDescriptionContaining(title, description);
    }

}
