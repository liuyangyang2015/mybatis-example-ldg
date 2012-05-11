package ldg.mybatis.controller;

import java.util.Calendar;
import java.util.List;

import ldg.mybatis.model.Comment;
import ldg.mybatis.service.CommentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/select.*", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView select(Long commentNo) {
		ModelAndView mav = new ModelAndView();

		try {
			List<Comment> comments = commentService.selectComment(commentNo);
			mav.addObject("message", comments);
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
		}
		return mav;
	}

	@RequestMapping(value = "/insert.*", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView insert(Comment comment) {
		ModelAndView mav = new ModelAndView();

		try {
			comment.setRegDate(Calendar.getInstance().getTime());
			Integer result = commentService.insertComment(comment);
			if (result > 0) {
				mav.addObject("message", "success");
			} else {
				mav.addObject("message", "fail");
			}
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
			mav.addObject("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/delete.*", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView delete(Long commentNo) {
		ModelAndView mav = new ModelAndView();

		try {
			Integer result = commentService.deleteComment(commentNo);
			if (result > 0) {
				mav.addObject("message", "success");
			} else {
				mav.addObject("message", "fail");
			}
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
			mav.addObject("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/jdbc.*", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView withSpringJdbc(Comment comment) {
		ModelAndView mav = new ModelAndView();

		try {
			comment.setRegDate(Calendar.getInstance().getTime());
			Integer result = commentService.withSpringJdbc(comment);
			if (result > 0) {
				mav.addObject("message", "success");
			} else {
				mav.addObject("message", "fail");
			}
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
			mav.addObject("message", e.getMessage());
		}
		return mav;
	}
}
