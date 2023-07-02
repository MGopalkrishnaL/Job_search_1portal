package com.Geekster.Job.search.portal.controller;

import com.Geekster.Job.search.portal.model.Job;
import com.Geekster.Job.search.portal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("/getAllJobs")
    public List<Job> getAllJobs() {
        return jobService.findAllJobs();
    }

    @PutMapping("/updateJob")
    public ResponseEntity<String> updateJob(@RequestParam Long id) {
        return jobService.updateJob(id);
    }

    @PostMapping("createJob")
    public ResponseEntity<Job> createJob(@Validated @RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Optional<Job> jobOptional = jobService.getJobById(id);
        return jobOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @Validated @RequestBody Job job) {
        Optional<Job> updatedJobOptional = jobService.updateJob(id, job);
        return updatedJobOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        if (jobService.deleteJob(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search/byTitle")
    public ResponseEntity<List<Job>> searchJobsByTitleContaining(@RequestParam String keyword) {
        List<Job> matchingJobs = jobService.findByTitleContaining(keyword);
        return ResponseEntity.ok(matchingJobs);
    }

    @GetMapping("/search/byDescription")
    public ResponseEntity<List<Job>> searchJobsByDescriptionContaining(@RequestParam String keyword) {
        List<Job> matchingJobs = jobService.findByDescriptionContaining(keyword);
        return ResponseEntity.ok(matchingJobs);
    }

    @GetMapping("/search/byLocation")
    public ResponseEntity<List<Job>> searchJobsByLocation(@RequestParam String location) {
        List<Job> matchingJobs = jobService.findByLocation(location);
        return ResponseEntity.ok(matchingJobs);
    }

    @GetMapping("/search/byCompanyName")
    public ResponseEntity<List<Job>> searchJobsByCompanyName(@RequestParam String companyName) {
        List<Job> matchingJobs = jobService.findByCompanyName(companyName);
        return ResponseEntity.ok(matchingJobs);
    }

    @PutMapping("/{id}/updateTitle")
    public ResponseEntity<Void> updateJobTitle(@PathVariable Long id, @RequestParam String title) {
        if (jobService.updateJobTitle(id, title)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByEmployerName")
    public ResponseEntity<Void> deleteJobsByEmployerName(@RequestParam String employerName) {
        if (jobService.deleteByEmployerName(employerName)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search/byCompanyEmailDomain")
    public ResponseEntity<List<Job>> searchJobsByCompanyEmailDomain(@RequestParam String domain) {
        List<Job> matchingJobs = jobService.findByCompanyEmailDomain(domain);
        return ResponseEntity.ok(matchingJobs);
    }

    @GetMapping("/search/byDescriptionKeywords")
    public ResponseEntity<List<Job>> searchJobsByDescriptionContainingKeywords(@RequestParam String keyword1, @RequestParam String keyword2) {
        List<Job> matchingJobs = jobService.findByDescriptionContainingKeywords(keyword1, keyword2);
        return ResponseEntity.ok(matchingJobs);
    }

    @GetMapping("/search/byTitleAndDescription")
    public ResponseEntity<List<Job>> searchJobsByTitleAndDescription(@RequestParam String title, @RequestParam String description) {
        List<Job> matchingJobs = jobService.findByTitleContainingAndDescriptionContaining(title, description);
        return ResponseEntity.ok(matchingJobs);
    }

}
