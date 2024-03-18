package com.memory.controller;

import com.memory.service.*;
import com.memory.service.DeadLockDemoService;
import com.memory.service.diskspace.DiskSpaceService;
import com.memory.service.fileconnectionleak.FileConnectionLeakService;
import com.memory.service.memoryleak.MemoryLeakDemoService;
import com.memory.service.metaspaceleak.MetaspaceLeakService;
import com.memory.service.network.NetworkLagService;
import com.memory.service.resttemplate.RestTemplateService;
import com.memory.service.stackoverflow.StackOverflowDemoService;
import com.memory.service.ThreadLeakDemoService;
import com.memory.service.webclient.WebClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/v1/invoke")
@AllArgsConstructor
@Slf4j
public class AppController {

	private com.memory.service.OOMCrashArrayService OOMCrashArrayService;
	private BlockedAppDemoService blockedAppDemoService;
	private CPUSpikeDemoService cpuSpikeDemoService;
	private DeadLockDemoService deadLockDemoService;
	private MemoryLeakDemoService memoryLeakDemoService;
	private MetaspaceLeakService metaspaceLeakService;
	private OOMCrashMapService oomCrashMapService;
	private OOMNoCrashService oomNoCrashService;
	private StackOverflowDemoService stackOverflowDemoService;
	private ThreadLeakDemoService threadLeakDemoService;
	private DBConnectionLeakService dbConnectionLeakService;
	private HttpConnectionLeak httpConnectionLeak;
	private FileConnectionLeakService fileConnectionLeakService;
	private WebClientService webClientService;
	private RestTemplateService restClientService;
	private NetworkLagService networkLagService;
	private DiskSpaceService diskSpaceService;

	@PostMapping("/process")
	public ResponseEntity<String> processFile() {
		// @RequestParam("filePath")

		String filePath = "src/main/resources/text.txt";
		try {
			OOMCrashArrayService.processFile(filePath);
			return ResponseEntity.ok("File processing started for: " + filePath);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to process file: " + e.getMessage());
		}
	}

	@GetMapping(value = "blocked-state", produces = { "application/json" })
	public ResponseEntity<Void>  invokeBlockedState() {
		log.info("Starting blocked app demo");
		blockedAppDemoService.startBlockingApp();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "cpu-spike", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeSpikeCpu() {
		log.info("Starting cpu spike demo");
		cpuSpikeDemoService.startWithThread();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/induceOOM")
	public void getRefDataByIds(Map<String, List<Integer>> refIds) {

		OOMCrashArrayService.simulateMemoryLeak();
	}

	@RequestMapping(value = "oom-crash", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeOOMCrash() {
		log.info("OOM With Crash");
		oomCrashMapService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "oom-no-crash", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeoomcrash() throws Exception {
		log.info("OOM No Crash demo");
		oomNoCrashService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "db-connections-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  leakSQLConnections() throws Exception {
		log.info("DB Connections Leak With Postgreas DB running test schema with myDb as db name and querying student table");
		dbConnectionLeakService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "memory-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeMemoryLeak() {
		log.info("Memory leak demo");
		memoryLeakDemoService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "stack-overflow", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeStackOverflow() {
		log.info("Stack Overflow demo");
		stackOverflowDemoService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "deadlock", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeDeadLock() {
		log.info("Starting dead lock demo");
		deadLockDemoService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "thread-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeThreadLeak() {
		threadLeakDemoService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "meta-space-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  invokeMetaSpaceLeak() throws Exception {
		log.info("Memory leak demo");
		metaspaceLeakService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}



	@RequestMapping(value = "http-connections-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  leakHttpConnections() throws Exception {
		log.info("HTTP Connections Leak");
		httpConnectionLeak.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "disk-space-fill", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<String>  diskSpaceFill(@RequestParam("drive.location") String driveLocation,
			                                        @RequestParam("percentage.fill") Integer percentageFill) throws Exception {
		log.info("Disk Space Fill");
		if( percentageFill <=100) {
			diskSpaceService.fillDiskSpace(driveLocation,percentageFill);
			return new ResponseEntity<String>("",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Fill percentage cannot be more than 90",HttpStatus.BAD_REQUEST) ;
		}
	}
	
	@RequestMapping(value = "network-lag-proxy", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<String>  networkLag(@RequestParam("port") Integer port,
			                                        @RequestParam("delay") Integer delay) throws Exception {
		log.info("Network Lag Service");
		networkLagService.startNetworkLagProxy(port, delay);
		return new ResponseEntity<String>("",HttpStatus.OK);
	}

	@RequestMapping(value = "file-connections-leak", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  leakFileConnections() throws Exception {
		log.info("HTTP Connections Leak");
		fileConnectionLeakService.start();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "slow-endpoint", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  slowEndPoint(@RequestParam("delayInSeconds") Integer delayInSeconds) throws Exception {
		log.info("HTTP Connections Leak");
		Thread.sleep(delayInSeconds * 1000);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "image-upload", produces = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<String> handleImageUpload(@RequestParam("file") MultipartFile file) throws InterruptedException {
        // Process the uploaded image (save it, perform operations, etc.)
        // You can access the image data using file.getBytes()
		Thread.sleep(3 * 1000);
        // For simplicity, this example just returns a success message
        return ResponseEntity.ok("Image uploaded successfully!");
    }
	
	@RequestMapping(value = "WebClient-nio-hugeupload-connections", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  webClientHugeUploads(  @RequestParam("image.url") String imageUrl,
														@RequestParam("rest.url") String restUrl,
														@RequestParam("numberOfCalls") Integer numberOfCalls) throws Exception {
		log.info("HTTP Connections Leak");
		webClientService.loadWebClientCalls(numberOfCalls, restUrl, imageUrl);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "restClient-nio-hugeupload-connections", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Void>  restClientHugeUploads(  @RequestParam("image.url") String imageUrl,
			                                             @RequestParam("rest.url") String restUrl,
														 @RequestParam("numberOfCalls") Integer numberOfCalls) throws Exception {
		log.info("HTTP Connections Leak");
		restClientService.loadRestClientCallsWithThreads(numberOfCalls, restUrl, imageUrl);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}